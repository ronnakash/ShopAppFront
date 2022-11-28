package com.example.shopappfront.data;

import com.example.shopappfront.data.models.User;

public class UserRepository extends RepositoryWithId<User>{
    private static volatile UserRepository instance;

    private UserRepository() {
        super("/user");
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    @Override
    public User getById(int id) {
        User loggedInUser = AuthenticationRepository.getInstance().getLoggedInUser();
        if (loggedInUser.getId() == id)
            return loggedInUser;
        for (User user : models){
            if (user.getId() == id)
                return user;
        }
        throw new RuntimeException();
    }

    public void updateModel(User user){
        AuthenticationRepository authenticationRepository = AuthenticationRepository.getInstance();
        if (user.getId() == authenticationRepository.getUserId()){
            user.setToken(authenticationRepository.getLoggedInUser().getToken());
            authenticationRepository.setLoggedInUser(user);
        }
        else super.updateModel(user);
    }

}
