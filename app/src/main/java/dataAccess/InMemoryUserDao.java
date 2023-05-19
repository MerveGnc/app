package dataAccess;

import java.util.ArrayList;
import java.util.List;

import entity.User;

public class InMemoryUserDao implements UserDao{
    private List<User> users;
	
	  public InMemoryUserDao() {
	        users = new ArrayList<>();
	    }

	@Override
	public boolean saveUser(User user) {
		 // Kullanıcıyı kaydetme işlemini gerçekleştir
        // Örneğin, kullanıcıları bir liste içerisinde tutabilirsiniz
        // Burada kullanıcı adının benzersiz olup olmadığını kontrol etmek gibi ek kontroller yapabilirsiniz

        // Örneğin:
        if (isUsernameAvailable(user.getUsername())) {
            users.add(user);
            return true;
        } else {
            return false;
        }
	}

	@Override
	public User getUserByUsername(String username) {
		// Kullanıcıyı kullanıcı adına göre getirme işlemini gerçekleştir

        // Örneğin:
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }

        return null;
}
	@Override
	public boolean isUsernameAvailable(String username) {
        // Kullanıcı adının kullanılabilir olup olmadığını kontrol etme işlemini gerçekleştir
        // Örneğin, aynı kullanıcı adına sahip başka bir kullanıcı var mı diye kontrol edebilirsiniz

        // Örneğin:
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
}

