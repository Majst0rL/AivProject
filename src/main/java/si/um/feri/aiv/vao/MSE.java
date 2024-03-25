package si.um.feri.aiv.vao;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class MSE {

    public MSE(){this(0.0,0.0,"","","",0);}
    public MSE(double xcoordinates, double ycoordinates, String name, String surname, String eMail, long capacity) {
        this.xcoordinates = xcoordinates;
        this.ycoordinates = ycoordinates;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.capacity = capacity;
    }

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double xcoordinates;
    private double ycoordinates;
    private String name;
    private String surname;
    @Column(nullable = false, unique = true)
    private String email;
    private long capacity;
    private LocalDateTime timestamp=LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
}
