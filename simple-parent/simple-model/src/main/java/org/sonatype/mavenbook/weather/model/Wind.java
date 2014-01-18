package org.sonatype.mavenbook.weather.model;

import javax.persistence.*;

/**
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Wind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String chill;
    private String direction;
    private String speed;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_id", nullable = false)
    private Weather weather;

    public Wind() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }

    public final String getDirection() {
        return direction;
    }

    public final void setDirection(final String direction) {
        this.direction = direction;
    }

    public final String getSpeed() {
        return speed;
    }

    public final void setSpeed(final String speed) {
        this.speed = speed;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
