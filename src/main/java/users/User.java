package users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private final UserType type;
    private final String userId;
    private final String userName;
    private final String userPwd;

    public User(UserType type, String userId, String userName, String userPwd) {
        this.type = type;
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
    }
}
