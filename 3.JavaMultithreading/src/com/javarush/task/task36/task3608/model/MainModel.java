package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.model.service.UserService;
import com.javarush.task.task36.task3608.model.service.UserServiceImpl;
import java.util.List;

public class MainModel implements Model {
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return this.modelData;
    }

    @Override
    public void loadUsers() {
        this.modelData.setUsers(getAllUsers());
        this.modelData.setDisplayDeletedUserList(false);
    }

    @Override
    public void loadDeletedUsers() {
        this.modelData.setUsers(this.userService.getAllDeletedUsers());
        this.modelData.setDisplayDeletedUserList(true);
    }

    public void loadUserById(long userId) {
        this.modelData.setActiveUser(this.userService.getUsersById(userId));
    }

    @Override
    public void deleteUserById(long id) {
        this.userService.deleteUser(id);
        this.modelData.setUsers(getAllUsers());

    }

    @Override
    public void changeUserData(String name, long id, int level) {
        this.userService.createOrUpdateUser(name, id, level);
        this.modelData.setUsers(getAllUsers());
    }

    private List<User> getAllUsers() {
        return this.userService.filterOnlyActiveUsers(this.userService.getUsersBetweenLevels(1, 100));
    }
}
