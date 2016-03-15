package brandsma.kas.mazemod.command;

import brandsma.kas.mazemod.world.gen.GenerateMaze;
import brandsma.kas.mazemod.world.gen.GenerateTempleDesert;
import brandsma.kas.mazemod.world.gen.GenerateTempleJungle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class CommandsKM extends CommandBase
{
    private List<String> aliases = new ArrayList();
    private static boolean needsworldeditinfo = true;

    public CommandsKM()
    {
        this.aliases.add("km");
        this.aliases.add("KasslimsMazes");
    }

    public String getCommandName()
    {
        return (String)this.aliases.get(0);
    }

    public String getCommandUsage(ICommandSender icommandsender)
    {
        return "/" + (String)this.aliases.get(0) + " <command> [<parameters>]";
    }

    public List<String> getCommandAliases()
    {
        return this.aliases;
    }

    public void processCommand(ICommandSender sender, String[] params)
    {
        if (params.length == 0)
        {
            this.printInfo(sender);
        }
        else if (params[0].equalsIgnoreCase("help"))
        {
            if (params.length == 1)
            {
                this.printHelp(sender);
            }
            else if (params[1].equalsIgnoreCase("generate"))
            {
                this.printHeader(sender);

                if (needsworldeditinfo)
                {
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Beware, there is no undo command. It might be wise to install a mod for terrain regeneration in case you want to remove the structure again."));
                    needsworldeditinfo = false;
                }

                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "Generates a temple."));
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Correct usage: \'" + EnumChatFormatting.RED + "/km generate <type> <x> <z> [<forcegen>]" + EnumChatFormatting.GRAY + "\'"));
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Params: "));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "type" + EnumChatFormatting.GRAY + " -- The type temple to generate: desert, jungle."));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "x" + EnumChatFormatting.GRAY + " -- The x coordinate to generate on."));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "z" + EnumChatFormatting.GRAY + " -- The z coordinate to generate on."));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "[forcegen]" + EnumChatFormatting.GRAY + " -- true if you want it to generate regardless of hight difference and biome. (false by default)"));
                this.printFooter(sender);
            }
            else if (params[1].equalsIgnoreCase("generateMaze"))
            {
                this.printHeader(sender);

                if (needsworldeditinfo)
                {
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Beware, there is no undo command. It might be wise to install a mod for terrain regeneration in case you want to remove the structure again."));
                    needsworldeditinfo = false;
                }

                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "Generates a maze into a solid block of material."));
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Correct usage: \'" + EnumChatFormatting.RED + "/km generateMaze <x> <y> <z> [<startX> <startZ> <mazesize> <cellsize>]" + EnumChatFormatting.GRAY + "\'"));
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "Params: "));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "x" + EnumChatFormatting.GRAY + " -- The x coordinate to generate on."));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "y" + EnumChatFormatting.GRAY + " -- The y coordinate to generate on."));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "z" + EnumChatFormatting.GRAY + " -- The z coordinate to generate on."));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "[startX, startZ]" + EnumChatFormatting.GRAY + " -- The maze coordinates (measured in cells) to start generating at. Must be positive. (0 and 7 by default)"));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "[mazesize]" + EnumChatFormatting.GRAY + " -- The size (measured in cells) of the maze. Value must be higher than 1. (15 by default)"));
                sender.addChatMessage(new ChatComponentText("  " + EnumChatFormatting.DARK_GRAY + "[cellsize]" + EnumChatFormatting.GRAY + " -- The size (measured in blocks including both walls) of a single cell. Value of 3 or higher. (4 by default) Using 4 is recommended, there is a bug with the algorithm if you use 3."));
                this.printFooter(sender);
            }
            else
            {
                this.printProblem(sender, "Help for km-command: \'" + params[1] + "\' not found.");
            }
        }
        else
        {
            Integer x;
            Integer y;

            if (params[0].equalsIgnoreCase("generate"))
            {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "/km " + getStringFromArray(params)));

                if (params.length >= 4)
                {
                    x = tryParseInt(params[2]);
                    y = tryParseInt(params[3]);
                    Boolean z = Boolean.valueOf(false);

                    if (params.length >= 5)
                    {
                        z = Boolean.valueOf(Boolean.parseBoolean(params[4]));
                    }

                    if (x != null && y != null)
                    {
                        if (params[1].equalsIgnoreCase("desert"))
                        {
                            (new GenerateTempleDesert(sender.getEntityWorld(), new Random())).callGeneration(x.intValue(), y.intValue(), z.booleanValue());
                        }
                        else if (params[1].equalsIgnoreCase("jungle"))
                        {
                            (new GenerateTempleJungle(sender.getEntityWorld(), new Random())).callGeneration(x.intValue(), y.intValue(), z.booleanValue());
                        }
                        else
                        {
                            this.printProblem(sender, "Unknown structuretype.");
                        }
                    }
                    else
                    {
                        this.printProblem(sender, "x and/or z coordinate(s) not valid.");
                    }
                }
                else
                {
                    this.printProblem(sender, "Not enough parameters.");
                }
            }
            else if (params[0].equalsIgnoreCase("generateMaze"))
            {
                sender.addChatMessage(new ChatComponentText(EnumChatFormatting.WHITE + "/km " + getStringFromArray(params)));

                if (params.length >= 4)
                {
                    x = tryParseInt(params[1]);
                    y = tryParseInt(params[2]);
                    Integer z1 = tryParseInt(params[3]);
                    Integer startX;
                    Integer startZ;
                    Integer mazesize;
                    Integer cellsize;

                    if (params.length >= 8)
                    {
                        startX = tryParseInt(params[4]);
                        startZ = tryParseInt(params[5]);
                        mazesize = tryParseInt(params[6]);
                        cellsize = tryParseInt(params[7]);
                    }
                    else
                    {
                        startX = Integer.valueOf(0);
                        startZ = Integer.valueOf(7);
                        mazesize = Integer.valueOf(15);
                        cellsize = Integer.valueOf(4);
                    }

                    if (startX != null && startZ != null && mazesize != null && cellsize != null && x != null && y != null && z1 != null && startX.intValue() >= 0 && startZ.intValue() >= 0 && mazesize.intValue() >= 2 && cellsize.intValue() >= 3)
                    {
                        new GenerateMaze(sender.getEntityWorld(), new Random(), startX.intValue(), startZ.intValue(), mazesize.intValue(), cellsize.intValue(), x.intValue(), y.intValue(), z1.intValue(), 0);
                    }
                    else
                    {
                        this.printSyntaxProblem(sender, params, new Object[] {x, y, z1, startX == null ? startX : (startX.intValue() < 0 ? null : startX), startZ == null ? startZ : (startZ.intValue() < 0 ? null : startZ), mazesize == null ? mazesize : (mazesize.intValue() < 2 ? null : mazesize), cellsize == null ? cellsize : (cellsize.intValue() < 3 ? null : cellsize)});
                    }
                }
                else
                {
                    this.printProblem(sender, "Not enough parameters.");
                }
            }
            else
            {
                this.printProblem(sender, "Unknown command. use \'/km help\' for a list of available km-commands");
            }
        }
    }

    private void printHeader(ICommandSender sender)
    {
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "----------- " + EnumChatFormatting.DARK_BLUE + "[Kasslim\'s Mazes]" + EnumChatFormatting.GRAY + " -----------"));
    }

    private void printFooter(ICommandSender sender)
    {
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "----------- " + EnumChatFormatting.DARK_BLUE + "[=============]" + EnumChatFormatting.GRAY + " -----------"));
    }

    private void printHelp(ICommandSender sender)
    {
        this.printHeader(sender);
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + " /km" + EnumChatFormatting.GRAY + " -- displays this mod\'s basic information."));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + " /km help" + EnumChatFormatting.GRAY + " -- displays this."));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + " /km generate" + EnumChatFormatting.GRAY + " -- generates all kinds of things."));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + " /km generateMaze" + EnumChatFormatting.GRAY + " -- generates all kinds of things."));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + " *type \'" + EnumChatFormatting.RED + "/km help <command>" + EnumChatFormatting.GRAY + "\' for more info."));
        this.printFooter(sender);
    }

    private void printInfo(ICommandSender sender)
    {
        this.printHeader(sender);
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + " *Version: " + EnumChatFormatting.DARK_GRAY + "1.7.10_A"));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + " *Developed on Windows 7"));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + " *Created by Kasslim"));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + " *Thanks to Pahimar for teaching me the basics of minecraft modding."));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + " *Thanks to AidanBrady (who made Mekanism) for these beautiful prints"));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + " *Type \"/km help\" for help"));
        this.printFooter(sender);
    }

    private void printProblem(ICommandSender sender, String problem)
    {
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + problem));
    }

    private void printSyntaxProblem(ICommandSender sender, String[] params, Object ... o)
    {
        String output = EnumChatFormatting.RED + "Syntax error: \'" + EnumChatFormatting.RED + "/km " + params[0];
        int i = 1;
        Object[] arr$ = o;
        int len$ = o.length;

        for (int i$ = 0; i$ < len$; ++i$)
        {
            Object current = arr$[i$];
            output = output + " " + (current == null ? EnumChatFormatting.DARK_RED : EnumChatFormatting.GREEN) + params[i];
            ++i;
        }

        output = output + EnumChatFormatting.RED + "\'.";
        sender.addChatMessage(new ChatComponentText(output));
    }

    public static Integer tryParseInt(String text)
    {
        try
        {
            return new Integer(text);
        }
        catch (NumberFormatException var2)
        {
            return null;
        }
    }

    public static String getStringFromArray(String[] s)
    {
        String output = "";
        String[] arr$ = s;
        int len$ = s.length;

        for (int i$ = 0; i$ < len$; ++i$)
        {
            String current = arr$[i$];
            output = output + " " + current;
        }

        return output.substring(1);
    }
}
