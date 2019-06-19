package cn.edu.gdpt.myapplication;

import java.util.List;

public class WeatherResul {
    private String city;
    private WeatherRealTime realtime;
    private List<WeatherFuture> future;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public WeatherRealTime getRealtime() {
        return realtime;
    }

    public void setRealtime(WeatherRealTime realtime) {
        this.realtime = realtime;
    }

    public List<WeatherFuture> getFuture() {
        return future;
    }

    public void setFuture(List<WeatherFuture> future) {
        this.future = future;
    }



    public class WeatherRealTime {
        private String city;
        private String temperature;
        private String humidity;
        private String info;
        private String direct;
        private String power;
        private String aqi;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getDirect() {
            return direct;
        }

        public void setDirect(String direct) {
            this.direct = direct;
        }

        public String getPower() {
            return power;
        }

        public void setPower(String power) {
            this.power = power;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }
    }

    public class WeatherFuture {
        private String date;
        private String temperature;
        private String weather;
        private String direct;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getDirect() {
            return direct;
        }

        public void setDirect(String direct) {
            this.direct = direct;
        }
    }
}
