package io.github.dailystruggle.yaml;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * One case per YAML feature rejected by the in-house parser
 * (ADR-025 §Migration step 5). Asserts the {@code messageKey} so the
 * stable identifiers stay stable across refactors.
 */
class YamlUnsupportedFeatureTest {

    private static YamlParseException assertRejected(String src) {
        return assertThrows(YamlParseException.class, () -> YamlReader.parse(src));
    }

    @Test @DisplayName("rejects anchors with yaml.unsupported.anchor")
    void rejectsAnchor() {
        YamlParseException e = assertRejected("a: &anchor 1\n");
        assertEquals("yaml.unsupported.anchor", e.messageKey());
    }

    @Test @DisplayName("rejects aliases with yaml.unsupported.alias")
    void rejectsAlias() {
        YamlParseException e = assertRejected("a: *anchor\n");
        assertEquals("yaml.unsupported.alias", e.messageKey());
    }

    @Test @DisplayName("rejects merge keys with yaml.unsupported.mergeKey")
    void rejectsMergeKey() {
        YamlParseException e = assertRejected("<<: x\n");
        assertEquals("yaml.unsupported.mergeKey", e.messageKey());
    }

    @Test @DisplayName("rejects flow mappings with yaml.unsupported.flowMap")
    void rejectsFlowMap() {
        YamlParseException e = assertRejected("a: {b: 1}\n");
        assertEquals("yaml.unsupported.flowMap", e.messageKey());
    }

    @Test @DisplayName("rejects flow sequences with yaml.unsupported.flowSeq")
    void rejectsFlowSeq() {
        YamlParseException e = assertRejected("a: [1, 2]\n");
        assertEquals("yaml.unsupported.flowSeq", e.messageKey());
    }

    @Test @DisplayName("rejects tags with yaml.unsupported.tag")
    void rejectsTag() {
        YamlParseException e = assertRejected("a: !!str 1\n");
        assertEquals("yaml.unsupported.tag", e.messageKey());
    }

    @Test @DisplayName("rejects document separators with yaml.unsupported.docSep")
    void rejectsDocSep() {
        YamlParseException e = assertRejected("---\na: 1\n");
        assertEquals("yaml.unsupported.docSep", e.messageKey());
    }

    @Test @DisplayName("rejects block scalars with yaml.unsupported.blockScalar")
    void rejectsBlockScalar() {
        YamlParseException e = assertRejected("a: |\n  hello\n");
        assertEquals("yaml.unsupported.blockScalar", e.messageKey());
    }

    @Test @DisplayName("rejects tab-indented lines with yaml.syntax.tabIndent")
    void rejectsTabIndent() {
        YamlParseException e = assertRejected("a:\n\tb: 1\n");
        assertEquals("yaml.syntax.tabIndent", e.messageKey());
    }
}
