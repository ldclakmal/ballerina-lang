/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package internal.parser.tree;


/**
 * Produces a new tree by doing a depth-first traversal of the internal tree.
 * <p>
 * This is a generated class.
 *
 * @since 2.0.0
 */
public abstract class STTreeModifier extends STNodeTransformer<STNode> {

    @Override
    public STModulePartNode transform(
            STModulePartNode modulePartNode) {
        STNode members = modifyNode(modulePartNode.members);
        STNode eofToken = modifyNode(modulePartNode.eofToken);
        return modulePartNode.modify(
                members,
                eofToken);
    }

    @Override
    public STBasicValueNode transform(
            STBasicValueNode basicValueNode) {
        STNode value = modifyNode(basicValueNode.value);
        return basicValueNode.modify(
                basicValueNode.kind,
                value);
    }

    @Override
    public STTableNode transform(
            STTableNode tableNode) {
        STNode openBracket = modifyNode(tableNode.openBracket);
        STNode identifier = modifyNode(tableNode.identifier);
        STNode closeBracket = modifyNode(tableNode.closeBracket);
        STNode fields = modifyNode(tableNode.fields);
        return tableNode.modify(
                openBracket,
                identifier,
                closeBracket,
                fields);
    }

    @Override
    public STTableArrayNode transform(
            STTableArrayNode tableArrayNode) {
        STNode openBracket = modifyNode(tableArrayNode.openBracket);
        STNode identifier = modifyNode(tableArrayNode.identifier);
        STNode closeBracket = modifyNode(tableArrayNode.closeBracket);
        STNode fields = modifyNode(tableArrayNode.fields);
        return tableArrayNode.modify(
                openBracket,
                identifier,
                closeBracket,
                fields);
    }

    @Override
    public STKeyValue transform(
            STKeyValue keyValue) {
        STNode identifier = modifyNode(keyValue.identifier);
        STNode assign = modifyNode(keyValue.assign);
        STNode value = modifyNode(keyValue.value);
        return keyValue.modify(
                identifier,
                assign,
                value);
    }

    @Override
    public STArray transform(
            STArray array) {
        STNode openBracket = modifyNode(array.openBracket);
        STNode values = modifyNode(array.values);
        STNode closeBracket = modifyNode(array.closeBracket);
        return array.modify(
                openBracket,
                values,
                closeBracket);
    }

    // Tokens

    public STToken transform(STToken token) {
        return token;
    }

    public STIdentifierToken transform(STIdentifierToken identifier) {
        return identifier;
    }

    public STLiteralValueToken transform(STLiteralValueToken literalValueToken) {
        return literalValueToken;
    }

    public STDocumentationLineToken transform(STDocumentationLineToken documentationLineToken) {
        return documentationLineToken;
    }

    public STMissingToken transform(STMissingToken missingToken) {
        return missingToken;
    }

    // Misc

    public STNode transform(STNodeList nodeList) {
        return transformSyntaxNode(nodeList);
    }

    @Override
    protected STNode transformSyntaxNode(STNode node) {
        return node;
    }

    protected <T extends STNode> T modifyNode(T node) {
        if (node == null) {
            return null;
        }
        // TODO
        return (T) node.apply(this);
    }
}

