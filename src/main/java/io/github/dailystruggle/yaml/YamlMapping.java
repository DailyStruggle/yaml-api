package io.github.dailystruggle.yaml;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Mapping node: an ordered collection of {@code key: value} pairs.
 *
 * <p>Insertion order is preserved via {@link LinkedHashMap} — important
 * for idempotent re-emit per ADR-042. Keys are strings (YAML's YAML
 * subset does not permit non-scalar mapping keys).</p>
 *
 * <p>Block comments above the mapping itself live on this node;
 * comments above individual entries live on the entry's child node
 * (scalar / mapping / sequence).</p>
 */
public final class YamlMapping extends YamlNode {

    private final Map<String, YamlNode> entries = new LinkedHashMap<>();

    /**
     * Trailing comment lines (block comments that follow the last entry
     * but are still inside the mapping's indent scope, used at the end
     * of file to preserve a trailing comment block).
     */
    private final java.util.List<String> trailingComments = new java.util.ArrayList<>();

    public Set<String> keys() {
        return entries.keySet();
    }

    public Map<String, YamlNode> entries() {
        return entries;
    }

    public YamlNode get(String key) {
        return entries.get(key);
    }

    public void put(String key, YamlNode value) {
        entries.put(key, value);
    }

    public boolean containsKey(String key) {
        return entries.containsKey(key);
    }

    public void remove(String key) {
        entries.remove(key);
    }

    public java.util.List<String> trailingComments() {
        return trailingComments;
    }
}
