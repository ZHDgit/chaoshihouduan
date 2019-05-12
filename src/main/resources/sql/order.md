getMaxOrderId
===
*获取订单最大id
SELECT MAX(id) FROM cs_order

getOrderList
===
* 获取订单列表

select * from cs_order

getOrderDetail
===
* 获取订单详情

select co.id order_id,cg.id goods_id,cg.goods_name,cgo.num
from cs_order co,cs_goods cg,cs_goods_order cgo 
where co.id=cgo.order_id 
and cg.id=cgo.goods_id
and co.id=#orderId#