package app.labs.ex01;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {
	String idx;
    String userId;
    String userPwd;
    String userName;
    String userRole;
    List<String> userRoles;
    String userStatus;
    
}
