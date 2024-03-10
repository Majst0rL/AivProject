package si.um.feri.aiv.jsf;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import si.um.feri.aiv.dao.CommunityDao;
import si.um.feri.aiv.dao.CommunityMemoryDao;
import si.um.feri.aiv.dao.MSEMemoryDao;
import si.um.feri.aiv.vao.Community;
import si.um.feri.aiv.vao.MSE;
import jakarta.faces.annotation.FacesConfig;
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

    public List<MSE> getSelectedMSEs() {
        return selectedMSEs;
    }

    public void setSelectedMSEs(List<MSE> selectedMSEs) {
        this.selectedMSEs = selectedMSEs;
    }

    public List<Community> getAllCommunities() throws Exception {
        return dao.getAll();
    }


    public String saveCommunity() throws Exception {
        Community newCommunity = new Community();
        newCommunity.setCommunityName(selectedCommunity.getCommunityName());
        newCommunity.setBossName(selectedCommunity.getBossName());
        newCommunity.setBossSurname(selectedCommunity.getBossSurname());
        newCommunity.setBossEmail(selectedCommunity.getBossEmail());
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

    public String openAddMSEPage() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(facesContext, null, "addmse.xhtml");
        return null;
    }
    public String editCommunity(){
        selectedCommunity = dao.find(selectedCommunityName);
        if (selectedCommunity == null){
            selectedCommunity = new Community();
        }
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


