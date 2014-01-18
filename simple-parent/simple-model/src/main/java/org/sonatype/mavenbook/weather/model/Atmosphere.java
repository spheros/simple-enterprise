package org.sonatype.mavenbook.weather.model;

import javax.persistence.*;

/**
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Atmosphere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String humidity;
    private String visibility;
    private String pressure;
    private String rising;

    @OneToOne
    @JoinColumn(name = "weather_id", nullable = false)
    private Weather weather;

    public Atmosphere() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public final String getHumidity() {
        return humidity;
    }

    public final void setHumidity(final String humidity) {
        this.humidity = humidity;
    }

    public final String getVisibility() {
        return visibility;
    }

    public final void setVisibility(final String visibility) {
        this.visibility = visibility;
    }

    public final String getPressure() {
        return pressure;
    }

    public final void setPressure(final String pressure) {
        this.pressure = pressure;
    }

    public final String getRising() {
        return rising;
    }

    public final void setRising(final String rising) {
        this.rising = rising;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
