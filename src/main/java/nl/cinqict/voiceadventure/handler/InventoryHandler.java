package nl.cinqict.voiceadventure.handler;

import nl.cinqict.voiceadventure.message.Request;
import nl.cinqict.voiceadventure.message.State;
import nl.cinqict.voiceadventure.world.Item;

import java.util.Set;

public class InventoryHandler extends Handler {

    static final String EMPTY_INVENTORY = "There is nothing in your inventory.";
    static final String ONE_ITEM = "You carry a %s.";
    static final String MULTIPLE_ITEMS_START = "The following items are in your inventory: ";

    @Override
    public void updateState(Request request) {
        State state = request.getState();
        Set<Item> inventory = state.getInventory();

        if (inventory.size() == 0) {
            reply = EMPTY_INVENTORY;
        } else if (inventory.size() == 1) {
            reply = String.format(ONE_ITEM, inventory.iterator().next().getName());
        } else {

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(MULTIPLE_ITEMS_START);

            for (Item item : inventory) {
                stringBuilder.append(item.getName());
                stringBuilder.append(" ");
            }

            reply = stringBuilder.toString();
        }
    }

}

