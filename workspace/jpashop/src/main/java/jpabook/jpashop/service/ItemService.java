package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 병합 : 병합시 모든 속성 변경, 값이 없으면 null로 업데이트
     */
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    /**
     * 변경 감지 : 원하는 속성만 선택해서 변경 가능
     *            merge를 할 경우 Null값으로 update 될 위험이 있어 변경 감지 사용을 권장
     */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
          // 찾아오면 영속상태 -> flush를 날려 영속상태 변경점을 감지해 업데이트
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);

    }

    public List<Item> findItem(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }


}
