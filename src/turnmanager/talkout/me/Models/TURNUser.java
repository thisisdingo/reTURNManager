package turnmanager.talkout.me.Models;

public class TURNUser {

    private String username;
    private String password;

    private String realm;
    /* MODE:

        #   authorized   (user authorized)
        #   refused      (user denied access)
        #   restricted   (for when bandwidth limiting is implemented)

     */


    private String mode;


    public TURNUser(String username, String password, String realm, String mode) {
        this.username = username;
        this.password = password;
        this.realm = realm;
        this.mode = mode;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRealm() {
        return realm;
    }

    public String getMode() {
        return mode;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj instanceof TURNUser){
            TURNUser eUser = (TURNUser) obj;
            return (eUser.username.equals(this.username) && eUser.password.equals(this.password) && eUser.realm.equals(this.realm));
        }

        return false;

    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
