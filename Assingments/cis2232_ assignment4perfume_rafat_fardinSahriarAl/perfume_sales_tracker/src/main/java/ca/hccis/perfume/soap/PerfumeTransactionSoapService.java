package ca.hccis.perfume.soap;

import ca.hccis.perfume.jpa.entity.PerfumeTransaction;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface PerfumeTransactionSoapService {

    @WebMethod
    PerfumeTransaction findById(
            @WebParam(name = "id") int id
    );

}
