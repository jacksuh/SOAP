package br.com.caelum.estoque.modelo.item;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public class EstoqueWS {
    private ItemDao dao = new ItemDao();

    @WebMethod(operationName="todosOsItens")
    @WebResult(name="itens")
    public ListaItens getItens() {

        System.out.println("Chamando getItens()");
        return new ListaItens(dao.todosItens());
    }

}
