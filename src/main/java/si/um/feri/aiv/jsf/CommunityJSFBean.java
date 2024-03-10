package si.um.feri.aiv.jsf;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.aiv.dao.CommunityDao;
import si.um.feri.aiv.dao.CommunityMemoryDao;
import si.um.feri.aiv.dao.MSEMemoryDao;
import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;

@Named("communityJSFBean")
@SessionScoped
public class CommunityJSFBean implements Serializable {

    @Serial
    private static final long serialVersionUID = 5130344131631106204L;
    Logger log=Logger.getLogger(CommunityJSFBean.class.toString());

    private CommunityDao dao = new CommunityMemoryDao();

    private Community selectedCommunity = new Community();
    private String mseEmail; // Dodali smo lastnost za e-poštni naslov MSE

    private String selectedCommunityName;
    private List<MSE> selectedMSEs;

    private MSEMemoryDao mseDao = new MSEMemoryDao();

    private List<MSE> allMSEs; // List to store all MSEs

    // Other properties and methods...

    public List<MSE> getAllMSEs() {
        if (allMSEs == null) {
            allMSEs = mseDao.getAll();
        }
        return allMSEs;
    }
    public List<MSE> getSelectedMSEs() {
        return selectedMSEs;
    }

    public void setSelectedMSEs(List<MSE> selectedMSEs) {
        this.selectedMSEs = selectedMSEs;
    }

    public List<Community> getAllCommunities() throws Exception {
        return dao.getAll();
    }
   

    // Method to add MSE to the selected community

    public String saveCommunity() throws Exception {
        // Ustvarimo novo skupnost
        Community newCommunity = new Community();
        newCommunity.setCommunityName(selectedCommunity.getCommunityName());
        newCommunity.setBossName(selectedCommunity.getBossName());
        newCommunity.setBossSurname(selectedCommunity.getBossSurname());
        newCommunity.setBossEmail(selectedCommunity.getBossEmail());

        // Dodamo e-poštni naslov MSE
        MSE mse = new MSE();
        mse.setEmail(mseEmail);
        newCommunity.getIncludedMSEs().add(mse);

        // Shranimo skupnost
        dao.save(newCommunity);
        return "allcommunities";
    }

        public void deleteCommunity(Community o) throws Exception {
            dao.delete(o.getCommunityName());
        }

        public void setSelectedCommunityName(String communityName) throws Exception {
            selectedCommunityName = communityName;
            selectedCommunity = dao.find(communityName);
            if(selectedCommunity == null) selectedCommunity = new Community();
        }
    public String addCommunity() throws Exception {
        MSE existingMSE = dao.findMSEByEmail(mseEmail);

        if (existingMSE != null) {
            selectedCommunity.getIncludedMSEs().add(existingMSE);
        }

        mseEmail = "";

        return "allcommunities";
    }

    public String getSelectedCommunityName() {
            return selectedCommunityName;
        }
        public Community getSelectedCommunity() {
            return selectedCommunity;
        }
        public void setSelectedCommunity(Community selectedCommunity) {
            this.selectedCommunity = selectedCommunity;
        }

}


