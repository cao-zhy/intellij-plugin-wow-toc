package com.github.czy211.wowtoc;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TocBlock extends AbstractBlock {
    private SpacingBuilder spacingBuilder;
    private TocCodeStyleSettings customSettings;

    public TocBlock(@NotNull ASTNode node, SpacingBuilder spacingBuilder,
                    TocCodeStyleSettings customSettings) {
        super(node, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment());
        this.spacingBuilder = spacingBuilder;
        this.customSettings = customSettings;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new TocBlock(child, spacingBuilder, customSettings);
                blocks.add(block);
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block block, @NotNull Block block1) {
        return spacingBuilder.getSpacing(this, block, block1);
    }

    @Override
    public boolean isLeaf() {
        return false;
    }

    @Override
    public Indent getIndent() {
        return Indent.getAbsoluteNoneIndent();
    }
}
