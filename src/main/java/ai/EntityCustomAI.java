package ai;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@Mod(modid = "MazeAI", name = "MazeAI", version = "1.0.0", dependencies = "required-after:KM@1.7.10_A")

public class EntityCustomAI extends EntityAIBase {

	private final EntityCustomAI ourAI;
	
	public EntityCustomAI(EntityCustomAI ai) {
		ourAI = ai;
		setMutexBits(8);
		
		//Debug
		System.out.println("Custom AI constructor");
	}
	

	@Override
	public boolean shouldExecute() {
		return true;
	}
	
	@Override
	public void startExecuting() {
		//Debug
		System.out.println("AI startExecute()");
	}
	
	@SubscribeEvent
	public void tickPlayer(PlayerTickEvent e) {
		
	}
	
	//Helper Functions
	private void spawnItem() {
		
	}
	
	private void spawnFood() {
		
	}
	
	private boolean isHealthLow(EntityPlayer player) {
		if (player.getHealth() < 2.0f) {
			return true;
		}
		return false;
	}
	
	private boolean isFoodLow(EntityPlayer player) {
		int currFoodStats = player.getFoodStats().getFoodLevel();
		if (currFoodStats < 5) return true;
		return false;
	}
	
	
	
}
