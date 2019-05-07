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