package cn.edu.gdpt.myapplication;

public class WeatherResponse {
    private String reason;
    private WeatherResul result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public WeatherResul getResult() {
        return result;
    }

    public void setResult(WeatherResul result) {
        this.result = result;
    }
}
