package info.hccis.bus.pass.soap;

import info.hccis.bus.pass.dao.BusPassDAO;
import info.hccis.bus.pass.jpa.entity.BusPass;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "info.hccis.bus.pass.soap.BusPassService")
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
