package io.github.dailystruggle.yaml;

import java.util.ArrayList;
import java.util.List;

/**
 * Sequence node: an ordered list of child nodes (block-style only).
 *
 * <p>Per ADR-025 the YAML subset is block-style only — flow sequences
 * ({@code [a, b, c]}) are rejected at parse time.</p>
 */
public final class YamlSequence extends YamlNode {

    private final List<YamlNode> items = new ArrayList<>();

    public List<YamlNode> items() {
        return items;
    }

    public void add(YamlNode node) {
        items.add(node);
    }

    public int size() {
        return items.size();
    }

    public YamlNode get(int i) {
        return items.get(i);
    }
}
