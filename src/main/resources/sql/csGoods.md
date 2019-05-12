getGoodsList
===
* 获取商品列表

select 
@pageTag(){
    a.*
@} 
from
(
select * from cs_goods cg
@if(searchText!=null && searchText!=""){
    where cg.goods_name like '%#text(searchText)#%'
@} 
@if(searchText==null || searchText==""){
    @if(searchNumber!=0 && searchType==1){
        where cg.goods_category=#searchNumber#
    @} 
    @if(searchNumber!=0 && searchType==2){
        where cg.goods_type =#searchNumber#
    @}
@}
) a

getGoodsById
===
* 通过id查询商品

select * from cs_goods cg where cg.id=#goodsId#