package cn.edu.gdpt.myapplication;

public class LocationResponse {
    private String resultcode;
    private String reason;
    private CityEntity result;

    public class CityEntity {
        private String address;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }


    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public CityEntity getResult() {
        return result;
    }

    public void setResult(CityEntity result) {
        this.result = result;
    }
}
