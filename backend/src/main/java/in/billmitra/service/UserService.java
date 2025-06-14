package in.billmitra.service;

import in.billmitra.io.UserRequest;
import in.billmitra.io.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);

    String getUserRole(String email);

    List<UserResponse> readAllUsers();

    void deleteUser(String userId);
}
