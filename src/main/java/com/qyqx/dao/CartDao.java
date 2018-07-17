package com.qyqx.dao;

import com.qyqx.common.vo.ViewCart;
import com.qyqx.domain.Cart;
import com.qyqx.domain.CartDetail;
import com.qyqx.domain.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CartDao {
    //新建-创建购物车
    @Insert("insert into t_cart(uid,money) values(#{uid},#{money})")
    public int insert(Cart cart);

    //新增商品
    @Insert("insert into t_cart(cid,gid,num,money) values(#{cid},#{gid},#{num},#{money})")
    public int insert(CartDetail cd);

    //获取用户的购物车
    @Select("select * from t_cart where uid=#{uid}")
    @ResultType(Cart.class)
    public Cart queryByUid(int uid);

    //获取用户的购物车详情
    @Select("select cd.* from t_cartdetail cd left join t_cart c on cd.cid=c.id where c.uid=#{uid}")
    @ResultType(CartDetail.class)
    public List<CartDetail> queryByDetail(int uid);
    //删除购物车
    @Delete("delete from  t_cartdetail where cid=#{cid} and gid=#{gid}")
    public int delete(int cid,int gid,int num);

    //修改数量
    @Update("update t_cartdetail set num=num+#{num},money=money+#{money} where cid=#{cid} and gid=#{gid}")
    public int update(int cid, Goods gds, int num);

    //获取详情对象
    @Select("select * from t_cartdetail where cid=#{cid} and gid=#{gid}")
    @ResultType(CartDetail.class)
    public CartDetail queryByCdid(int cid,int gid);

    //购物车的数据
    @Select("select cd.num,cd.money,cd.gid,g.name,g.price from t_cartdetail cd left join t_goods g on cd.gid=g.id where cd.cid=#{cid}")
    @ResultType(ViewCart.class)
    public List<ViewCart> queryCart(int cid);

}
