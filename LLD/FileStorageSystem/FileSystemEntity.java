package LLD.FileStorageSystem;

import java.util.*;

public abstract class FileSystemEntity {
    protected String id;
    protected String name;
    protected Date creationDate;
    protected User creator;
    protected Map<String, PermissionType> permissionTypeMap;

    public FileSystemEntity(String name, User owner) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.creationDate = new Date();
        this.creator = owner;
        this.permissionTypeMap = new HashMap<>();
        this.permissionTypeMap.put(owner.getUserId(), PermissionType.OWNER);
    }

    public String getId() { return id; }

    public void setPermission(User user, PermissionType permission) {
        permissionTypeMap.put(user.getUserId(), permission);
    }

    public boolean hasPermission(User user, PermissionType required) {
        PermissionType actual = permissionTypeMap.get(user.getUserId());
        if (actual == null) return false;

        return actual.ordinal() >= required.ordinal();

    }

    public abstract void print(int indent);


}
