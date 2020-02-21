package map_debug_mod;

import basemod.DevConsole;
import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.screens.DungeonMapScreen;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.util.ArrayList;

// TODO: move to separate mod
@SpirePatch(
        clz= DungeonMapScreen.class,
        method="render"
)
public class NodeRenderPatch {
    private static boolean nodesHidden = true;
    @SpirePostfixPatch
    public static void Postfix(DungeonMapScreen __instance, SpriteBatch sb) {
        if(AbstractDungeon.screen == AbstractDungeon.CurrentScreen.MAP) {
            if (!DevConsole.visible && Gdx.input.isKeyJustPressed(Input.Keys.PERIOD)) {
                nodesHidden = !nodesHidden;
            }
            if (!nodesHidden) {
                ArrayList<MapRoomNode> nodes = (ArrayList<MapRoomNode>)
                        ReflectionHacks.getPrivate(__instance, DungeonMapScreen.class, "visibleMapNodes");
                for (MapRoomNode node : nodes) {
                    String message = "(" + node.x + ", " + node.y + ")";
                    FontHelper.renderFont(sb, FontHelper.charDescFont, message, node.hb.x, node.hb.y, Color.BLUE);
                }
            }
        }
    }
}