package ca.hccis.perfume.soap;

import ca.hccis.perfume.dao.BusPassDAO;
import ca.hccis.perfume.jpa.entity.BusPass;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "soap.ca.hccis.perfume.BusPassService")
public class BusPassServiceImpl implements BusPassService {

    public BusPass getBusPass(int id) {

        BusPassDAO busPassDAO = new BusPassDAO();
        BusPass busPass = null;
        for(BusPass current: getBusPasses()){
            if(current.getId().equals(id)){
                busPass = current;
            }
        }
        return busPass;

    }

    @Override
    public int getCount() {
        return getBusPasses().size();
    }

    @Override
    public List<BusPass> getBusPasses() {

        BusPassDAO busPassDAO = new BusPassDAO();
        ArrayList<BusPass> busPasses = busPassDAO.selectAll();
        return busPasses;


    }

}
