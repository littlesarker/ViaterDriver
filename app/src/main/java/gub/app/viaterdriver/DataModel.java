package gub.app.viaterdriver;

public class DataModel {

    private String mID,mFrom_lat,mFrom_lng,mto_lat,mto_lng,mAdditionalInfo,mCreatedAT,mDeparture_time,mStatus;

    private  int mBudget;

    public DataModel(){

    }

    public DataModel(String mID, String mFrom_lat, String mFrom_lng, String mto_lat, String mto_lng, String mAdditionalInfo, String mCreatedAT, String mDeparture_time, String mStatus, int mBudget) {
        this.mID = mID;
        this.mFrom_lat = mFrom_lat;
        this.mFrom_lng = mFrom_lng;
        this.mto_lat = mto_lat;
        this.mto_lng = mto_lng;
        this.mAdditionalInfo = mAdditionalInfo;
        this.mCreatedAT = mCreatedAT;
        this.mDeparture_time = mDeparture_time;
        this.mStatus = mStatus;
        this.mBudget = mBudget;
    }

    public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmFrom_lat() {
        return mFrom_lat;
    }

    public void setmFrom_lat(String mFrom_lat) {
        this.mFrom_lat = mFrom_lat;
    }

    public String getmFrom_lng() {
        return mFrom_lng;
    }

    public void setmFrom_lng(String mFrom_lng) {
        this.mFrom_lng = mFrom_lng;
    }

    public String getMto_lat() {
        return mto_lat;
    }

    public void setMto_lat(String mto_lat) {
        this.mto_lat = mto_lat;
    }

    public String getMto_lng() {
        return mto_lng;
    }

    public void setMto_lng(String mto_lng) {
        this.mto_lng = mto_lng;
    }

    public String getmAdditionalInfo() {
        return mAdditionalInfo;
    }

    public void setmAdditionalInfo(String mAdditionalInfo) {
        this.mAdditionalInfo = mAdditionalInfo;
    }

    public String getmCreatedAT() {
        return mCreatedAT;
    }

    public void setmCreatedAT(String mCreatedAT) {
        this.mCreatedAT = mCreatedAT;
    }

    public String getmDeparture_time() {
        return mDeparture_time;
    }

    public void setmDeparture_time(String mDeparture_time) {
        this.mDeparture_time = mDeparture_time;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public int getmBudget() {
        return mBudget;
    }

    public void setmBudget(int mBudget) {
        this.mBudget = mBudget;
    }
}
