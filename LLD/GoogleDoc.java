package LLD;

// Google Docs LLD Simple
// One class only
// Insert + Delete
// With versioning

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

class GoogleDoc {
    // User info
    static class User {
        String id;
        String name;
        User(String id,String n){
            this.id=id;
            this.name=n;
        }
    }

    // Doc model
    static class Document {
        String id;
        StringBuilder txt=
                new StringBuilder();
        AtomicInteger ver=
                new AtomicInteger(0);
        ReentrantReadWriteLock rw=
                new ReentrantReadWriteLock();
        Document(String id,String init){
            this.id=id;
            this.txt.append(init);
        }
    }

    // Store docs
    static class DocStore {
        Map<String,Document> docs=
                new ConcurrentHashMap<>();
        Document create(String init){
            String id=UUID.randomUUID().toString();
            Document d=new Document(id,init);
            docs.put(id,d);
            return d;
        }
        Document get(String id){
            return docs.get(id);
        }
    }

    // Base op
    static abstract class EditOp {
        String did;
        String uid;
        int base;
        EditOp(String d,String u,int b){
            this.did=d;
            this.uid=u;
            this.base=b;
        }
        abstract void apply(Document d);
    }

    // Insert op
    static class InsertOp extends EditOp {
        int pos;
        String txt;
        InsertOp(String d,String u,int b,int p,String t){
            super(d,u,b);
            this.pos=p;
            this.txt=t;
        }
        void apply(Document d){
            int p=Math.min(pos,d.txt.length());
            d.txt.insert(p,txt);
        }
    }

    // Delete op
    static class DeleteOp extends EditOp {
        int pos;
        int len;
        DeleteOp(String d,String u,int b,int p,int l){
            super(d,u,b);
            this.pos=p;
            this.len=l;
        }
        void apply(Document d){
            int p=Math.min(pos,d.txt.length());
            int l=Math.min(len,d.txt.length()-p);
            if(l>0) d.txt.delete(p,p+l);
        }
    }

    // Collab mgr
    static class CollabMgr {
        DocStore st;
        CollabMgr(DocStore s){st=s;}
        int submit(EditOp op)throws Exception{
            Document d=st.get(op.did);
            if(d==null) throw new Exception("no doc");
            d.rw.writeLock().lock();
            try{
                int v=d.ver.get();
                if(op.base!=v)
                    throw new Exception("ver mismatch");
                op.apply(d);
                return d.ver.incrementAndGet();
            }finally{
                d.rw.writeLock().unlock();
            }
        }
        String read(String did){
            Document d=st.get(did);
            if(d==null) return null;
            d.rw.readLock().lock();
            try{ return d.txt.toString(); }
            finally{ d.rw.readLock().unlock(); }
        }
    }

    // Main demo
    public static void main(String[] a)throws Exception{
        DocStore st=new DocStore();
        CollabMgr cm=new CollabMgr(st);
        User a1=new User("u1","Alice");
        User b1=new User("u2","Bob");
        Document d=st.create("Hello World");

        // Alice insert
        int v1=cm.submit(
                new InsertOp(d.id,a1.id,0,5,", A"));
        System.out.println("After A v="+v1+":"+cm.read(d.id));

        // Bob delete
        int v2=cm.submit(
                new DeleteOp(d.id,b1.id,v1,0,5));
        System.out.println("After B v="+v2+":"+cm.read(d.id));
    }
}

/*
Notes:
- One class only
- Insert & Delete
- Ver check safe
- Locks for sync
- Easy to extend
*/

