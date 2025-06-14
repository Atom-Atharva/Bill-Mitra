package in.billmitra.service;

import in.billmitra.io.UserRequest;
import in.billmitra.io.UserResponse;

import java.util.List;

public interface UserService {
    public UserResponse createUser(UserRequest request);

    public String getUserRole(String email);

    public List<UserResponse> readAllUsers();

    public void deleteUser(String userId);
}
