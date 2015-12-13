package com.tayjay.minecards.card;

import com.tayjay.minecards.init.ModItems;
import com.tayjay.minecards.util.LogHelper;
import com.tayjay.minecards.util.NBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.LinkedHashMap;

/**
 * Created by tayjm_000 on 2015-10-18.
 */
public class Card
{
    private String cardName;
    private Rarity rarity;
    private Set set;
    private ItemStack itemForCard;
    private EntityLiving entityLivingForCard;


    /**
     * Create a new Card.
     * @param name
     * @param rarity
     * @param set
     */
    public Card(String name, Rarity rarity, Set set)
    {
        this.cardName = name;
        this.rarity = rarity;
        this.set = set;
        this.itemForCard = null;
        this.entityLivingForCard = null;
        cards.put(name, this);
    }

    public Card(String name, Rarity rarity, Set set, ItemStack itemForCard)
    {
        this.cardName = name;
        this.rarity = rarity;
        this.set = set;
        this.itemForCard = itemForCard;
        this.entityLivingForCard = null;
        cards.put(name, this);
    }
    public Card(String name, Rarity rarity, Set set, EntityLiving entityLivingForCard)
    {
        this.cardName = name;
        this.rarity = rarity;
        this.set = set;
        this.itemForCard = null;
        this.entityLivingForCard = entityLivingForCard;
        cards.put(name, this);
    }

    public String getCardName()
    {
        return cardName;
    }

    public Rarity getRarity()
    {
        return rarity;
    }

    public String getRarityName()
    {
        return rarity.getName();
    }

    public Set getSet()
    {
        return set;
    }

    public String getSetName()
    {
        return set.getSetName();
    }

    public ItemStack getItemForCard()
    {
        if(itemForCard==null)
        {
            LogHelper.debug("This card does not have an item.");
        }
        return itemForCard;
    }

    public EntityLiving getEntityLivingForCard()
    {
        if(entityLivingForCard==null)
        {
            LogHelper.debug("This card does not have an Entity Living.");
        }
        return entityLivingForCard;
    }

    public void readFromItemStack(ItemStack i)
    {
        this.cardName = NBTHelper.getString(i,"cardName");
        this.rarity = Rarity.rarities.get(NBTHelper.getString(i, "rarityName"));
        this.set = Set.sets.get(NBTHelper.getString(i,"setName"));
    }

    public void writeToItemStack(ItemStack i)
    {
        NBTHelper.setString(i,"cardName",this.cardName);
        NBTHelper.setString(i,"rarityName",this.getRarityName());
        NBTHelper.setString(i,"setName",this.getSetName());
    }


    public static World world = Minecraft.getMinecraft().theWorld;

    public static LinkedHashMap<String, Card> cards = new LinkedHashMap<String, Card>();
    public static final Card DIRT =         new Card("Dirt",        Rarity.COMMON,  Set.BLOCKS,     new ItemStack(Blocks.dirt));
    public static final Card GRASS =        new Card("Grass",       Rarity.COMMON,  Set.BLOCKS,     new ItemStack(Blocks.grass));
    public static final Card COBBLESTONE =  new Card("Cobblestone", Rarity.COMMON,  Set.BLOCKS,     new ItemStack(Blocks.cobblestone));
    public static final Card STONE =        new Card("Stone",       Rarity.COMMON,  Set.BLOCKS,     new ItemStack(Blocks.stone));
    public static final Card DIAMOND_SWORD= new Card("Diamond Sword",Rarity.RARE,   Set.SWORDS,     new ItemStack(Items.diamond_sword));
    public static final Card COW =          new Card("Cow",         Rarity.COMMON,  Set.ANIMALS,    new EntityCow(world));
    public static final Card SHEEP =       new Card("Sheep",        Rarity.COMMON,  Set.ANIMALS,    new EntitySheep(world));
    public static final Card HORSE =        new Card("Horse",       Rarity.UNCOMMON,Set.ANIMALS,    new EntityHorse(world));
    public static final Card ZOMBIE =       new Card("Zombie",      Rarity.COMMON,  Set.MOBS,       new EntityZombie(world));
    public static EntitySkeleton skel;
    public static EntitySkeleton witherSkel;

    static {
        skel = new EntitySkeleton(world);
        //skel.setSkeletonType(1);
        skel.setCurrentItemOrArmor(0,new ItemStack(Items.bow));
        witherSkel = new EntitySkeleton(world);
        witherSkel.setSkeletonType(1);
        witherSkel.setCurrentItemOrArmor(0,new ItemStack(Items.stone_sword));
    }
    public static final Card SKELETON =       new Card("Skeleton",  Rarity.COMMON,  Set.MOBS,       skel);
    public static final Card WITHER_SKELETON = new Card("Wither Skeleton", Rarity.RARE,Set.MOBS, witherSkel);
    public static final Card WITHER_BOSS = new Card("Wither",Rarity.EPIC,Set.BOSS,new EntityWither(world));
    public static final Card DRAGON = new Card("Dragon",Rarity.LEGENDARY,Set.BOSS,new EntityDragon(world));



    public static class Set
    {
        public String setName;
        public boolean hasEntity;

        public Set(String setName)
        {
            this(setName,false);
        }

        public Set(String setName, boolean hasEntity)
        {
            this.setName = setName;
            this.hasEntity = hasEntity;
            sets.put(setName,this);
        }

        public String getSetName()
        {
            return this.setName;
        }

        public static LinkedHashMap<String, Set> sets = new LinkedHashMap<String, Set>();
        public static final Set BLOCKS = new Set("Blocks");
        public static final Set ARMOUR = new Set("Armour");
        public static final Set SWORDS = new Set("Swords");
        public static final Set ANIMALS = new Set("Animals",true);
        public static final Set MOBS = new Set("Mobs",true);
        public static final Set BOSS = new Set("Boss",true);
    }



    public static class Rarity
    {
        //COMMON,UNCOMMON,RARE,EPIC,LEGENDARY
        int level;
        String name;

        public Rarity(int level, String name)
        {
            this.level = level;
            this.name = name;
            rarities.put(name,this);
        }

        public int getLevel()
        {
            return level;
        }

        public String getName()
        {
            return name;
        }

        public static LinkedHashMap<String, Rarity> rarities = new LinkedHashMap<String, Rarity>();
        public static final Rarity COMMON = new Rarity(0,"Common");
        public static final Rarity UNCOMMON = new Rarity(0,"Uncommon");
        public static final Rarity RARE = new Rarity(0,"Rare");
        public static final Rarity EPIC = new Rarity(0,"Epic");
        public static final Rarity LEGENDARY = new Rarity(0,"Legendary");

    }
}
