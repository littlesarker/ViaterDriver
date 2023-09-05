package gub.app.viaterdriver;

public class Heros {

    String requestI,rbudget,rInfo;

    public Heros(String requestI, String rbudget, String rInfo) {
        this.requestI = requestI;
        this.rbudget = rbudget;
        this.rInfo = rInfo;
    }

    public String getRequestI() {
        return requestI;
    }

    public void setRequestI(String requestI) {
        this.requestI = requestI;
    }

    public String getRbudget() {
        return rbudget;
    }

    public void setRbudget(String rbudget) {
        this.rbudget = rbudget;
    }

    public String getrInfo() {
        return rInfo;
    }

    public void setrInfo(String rInfo) {
        this.rInfo = rInfo;
    }
}
