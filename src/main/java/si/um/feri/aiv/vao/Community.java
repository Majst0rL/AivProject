package si.um.feri.aiv.vao;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import si.um.feri.aiv.obs.Observable;
import si.um.feri.aiv.obs.Observer;

@Getter
@Setter
@ToString
@Entity
public class Community  { //implements Observable

//    private List<Observer> observers = new ArrayList<>();
    public Community(){this("",new ArrayList<>(),"","","");}
    public Community(String communityName, List<MSE> includedMSEs, String bossName, String bossSurname, String bossEmail) {

        this.communityName = communityName;
        this.includedMSEs = includedMSEs;
        this.bossName = bossName;
        this.bossSurname = bossSurname;
        this.bossEmail = bossEmail;
    }

    @NotBlank

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String communityName;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MSE> includedMSEs = new ArrayList<>();

    private String bossName;
    private String bossSurname;
    private String bossEmail;

//    @Override
//    public void addObserver(Observer o) {
//        observers.add(o);
//    }
//    @Override
//    public void removeObserver(Observer o) {
//        observers.remove(o);
//    }
//    @Override
//    public void notifyObservers() {
//        for (Observer observer : observers) {
//            observer.update(this);
//        }
//    }
}
