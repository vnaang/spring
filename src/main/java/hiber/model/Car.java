package hiber.model;

import javax.persistence.*;

@Entity
@Table(name = "user_car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series")
    int series;

    @Column(name = "model")
    String model;
    
    @OneToOne(mappedBy = "car",fetch = FetchType.LAZY)

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car(){}

    public Car(String model, int series){
        this.model= model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) { this.model = model; }

    public int getSeries() { return series; }

    public void setSeries(int series) { this.series = series; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Override
    public String toString() {
        return model + series ;
    }
}
