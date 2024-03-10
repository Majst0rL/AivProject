package si.um.feri.aiv.vao;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Community {

    public Community(){this("",new ArrayList<>(),"","","");}
    public Community(String communityName, List<MSE> includedMSEs, String bossName, String bossSurname, String bossEmail) {

        this.communityName = communityName;
        this.includedMSEs = includedMSEs;
        this.bossName = bossName;
        this.bossSurname = bossSurname;
        this.bossEmail = bossEmail;
    }

    @NotBlank

    private String communityName;
    private List<MSE> includedMSEs;
    private String bossName;
    private String bossSurname;
    private String bossEmail;

}
