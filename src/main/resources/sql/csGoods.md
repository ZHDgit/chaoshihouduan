getGoodsList
===
* 获取商品列表
select * from cs_goods cg
@if(searchNumber!=0 && searchType==1){
    where cg.goods_category=#searchNumber#
@} 
@if(searchNumber!=0 && searchType==2){
    where cg.goods_type =#searchNumber#
@} 

getGoodsById
===
* 通过id查询商品

select * from cs_goods cg where cg.id=#goodsId#