package cashtime;


public class Team {

    private int teamId;
    private String teamName;
    private String captainName;
    private String contactNumber;
    private String location;
    private String province;

    public Team(int teamId, String teamName,
                String captainName, String contactNumber,
                String location, String province) {

        this.teamId = teamId;
        this.teamName = teamName;
        this.captainName = captainName;
        this.contactNumber = contactNumber;
        this.location = location;
        this.province = province;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getCaptainName() {
        return captainName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getLocation() {
        return location;
    }

    public String getProvince() {
        return province;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}

