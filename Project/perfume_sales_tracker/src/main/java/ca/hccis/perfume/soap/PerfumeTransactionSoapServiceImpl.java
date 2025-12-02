package ca.hccis.perfume.soap;
import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import ca.hccis.perfume.dao.PerfumeTransactionDAO; // You must create DAO
import javax.jws.WebService;

@WebService(endpointInterface = "ca.hccis.perfume.soap.PerfumeTransactionSoapService")
public class PerfumeTransactionSoapServiceImpl implements PerfumeTransactionSoapService {

    @Override
    public PerfumeTransaction findById(int id) {

        PerfumeTransactionDAO dao = new PerfumeTransactionDAO();
       return dao.findById(id); // you write this method
    }

}
