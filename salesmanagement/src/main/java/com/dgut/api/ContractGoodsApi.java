/*
package com.dgut.api;



import com.dgut.dao.ContractDaoImpl;
import com.dgut.dao.GoodsDaoImpl;
import com.dgut.dao.PurchaseListDao;
import com.dgut.entity.Contract;
import com.dgut.entity.Goods;
import com.dgut.entity.PurchaseListItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Path("api/contracts/*")
//@Produces(MediaType.APPLICATION_JSON)
public class ContractGoodsApi extends HttpServlet {
    private final ContractDaoImpl contractDao = new ContractDaoImpl();
    private final PurchaseListDao purchaseListItemDao = new PurchaseListDao();
    private final GoodsDaoImpl goodsDao = new GoodsDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 解析请求路径，获取id参数
        String requestURI = request.getRequestURI();
//        String id = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        int contractId = Integer.parseInt(requestURI.substring(requestURI.lastIndexOf("/") + 1));
        Contract contract = contractDao.getContractById(contractId);

        if (contract == null) {
            response.setStatus(404);
            response.getWriter().println("Contract not found");
            return;
        }

        List<PurchaseListItem> purchaseListItems = null;

        try {
            purchaseListItems = purchaseListItemDao.getPurchaseListItemsByPurchaseListId(contract.getPurchaseListId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Map<Integer, Integer> goodsMap = new HashMap<>();

        for (PurchaseListItem item : purchaseListItems) {
            goodsMap.put(item.getGoodsId(), item.getQuantity());
        }

        List<Goods> goodsList = goodsDao.findAll();
        request.setAttribute("goodsList", goodsList);
        request.setAttribute("goodsMap", goodsMap);
        request.setAttribute("contract", contract);

        request.getRequestDispatcher("/contract.jsp").forward(request, response);
    }
//
//    private final ContractDaoImpl contractDao;
//    private final PurchaseListDao purchaseListDao;
//
//    public ContractGoodsApi(ContractDaoImpl contractDao, PurchaseListDao purchaseListDao) {
//        this.contractDao = contractDao;
//        this.purchaseListDao = purchaseListDao;
//    }
//
//    @GET
//    public Response getGoodsList(@PathParam("id") int contractId) throws SQLException {
        Contract contract = contractDao.getContractById(contractId);
        if (contract == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<PurchaseListItem> purchaseListItems = purchaseListDao.getPurchaseListItemsByPurchaseListId(contract.getPurchaseListId());

        Map<Integer, Integer> goodsMap = new HashMap<>();
        for (PurchaseListItem purchaseListItem : purchaseListItems) {
            int goodsId = purchaseListItem.getGoodsId();
            int quantity = purchaseListItem.getQuantity();
            if (goodsMap.containsKey(goodsId)) {
                goodsMap.put(goodsId, goodsMap.get(goodsId) + quantity);
            } else {
                goodsMap.put(goodsId, quantity);
            }
        }

        return Response.ok(goodsMap).build();
//    }
}
*/