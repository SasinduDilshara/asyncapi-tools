/*
 * Copyright (c) 2022, WSO2 LLC. (http://www.wso2.com). All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.ballerina.asyncapi.core.generators.client;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This class is used to generate util file syntax tree according to the generated client.
 *
 * @since 1.3.0
 */
public class BallerinaUtilGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BallerinaUtilGenerator.class);
    private static final String CREATE_FORM_URLENCODED_REQUEST_BODY = "createFormURLEncodedRequestBody";
    private static final String GET_DEEP_OBJECT_STYLE_REQUEST = "getDeepObjectStyleRequest";
    private static final String GET_FORM_STYLE_REQUEST = "getFormStyleRequest";
    private static final String GET_SERIALIZED_ARRAY = "getSerializedArray";
    private static final String GET_ENCODED_URI = "getEncodedUri";
    private static final String GET_ORIGINAL_KEY = "getOriginalKey";
    private static final String GET_PATH_FOR_QUERY_PARAM = "getPathForQueryParam";
    private static final String GET_MAP_FOR_HEADERS = "getMapForHeaders";
    private static final String GET_SERIALIZED_RECORD_ARRAY = "getSerializedRecordArray";
    private static final String CREATE_MULTIPART_BODY_PARTS = "createBodyParts";
    private final boolean headersFound = false;
    private boolean pathParametersFound = false;
    private final boolean queryParamsFound = false;
    private final boolean requestBodyEncodingFound = false;
    private final boolean requestBodyMultipartFormDatafound = false;

    /**
     //     * Set `queryParamsFound` flag to `true` when at least one query parameter found.
     //     *
     //     * @param flag Function will be called only in the occasions where flag needs to be set to `true`
     //     */
//    public void setQueryParamsFound(boolean flag) {
//        this.queryParamsFound = flag;
//    }
//
//    /**
//     * Set `headersFound` flag to `true` when at least one header found.
//     *
//     * @param flag Function will be called only in the occasions where flag needs to be set to `true`
//     */
//    public void setHeadersFound(boolean flag) {
//
//        this.headersFound = flag;
//    }
//

    /**
     * Set `pathParametersFound` flag to `true` when at least one path parameter found.
     *
     * @param flag Function will be called only in the occasions where flag needs to be set to `true`
     */
    public void setPathParametersFound(boolean flag) {
        this.pathParametersFound = flag;
    }
//
//    /**
//     * Set `setRequestBodyEncodingFound` flag to `true` when at least one function found with URL-encoded
//     request body.
//     *
//     * @param flag Function will be called only in the occasions where value needs to be set to `true`.
//     */
//    public void setRequestBodyEncodingFound(boolean flag) {
//        this.requestBodyEncodingFound = flag;
//    }
//
//    /**
//     * Set `setRequestBodyMultipartFormDatafound` flag to `true` when at least one function found with multipart
//     * form-data request body.
//     *
//     * @param flag Function will be called only in the occasions where value needs to be set to `true`.
//     */
//    public void setRequestBodyMultipartFormDatafound(boolean flag) {
//        this.requestBodyMultipartFormDatafound = flag;
//    }
//
//    /**
//     * Generates util file syntax tree.
//     *
//     * @return Syntax tree of the util.bal file
//     */
//    public SyntaxTree generateUtilSyntaxTree() throws IOException {
//        Set<String> functionNameList = new LinkedHashSet<>();
//        if (requestBodyEncodingFound) {
//            functionNameList.addAll(Arrays.asList(
//                    CREATE_FORM_URLENCODED_REQUEST_BODY, GET_DEEP_OBJECT_STYLE_REQUEST, GET_FORM_STYLE_REQUEST,
//                    GET_ENCODED_URI, GET_ORIGINAL_KEY, GET_SERIALIZED_ARRAY, GET_SERIALIZED_RECORD_ARRAY
//                                                 ));
//        }
//        if (queryParamsFound) {
//            functionNameList.addAll(Arrays.asList(
//                    GET_DEEP_OBJECT_STYLE_REQUEST, GET_FORM_STYLE_REQUEST,
//                    GET_ENCODED_URI, GET_ORIGINAL_KEY, GET_SERIALIZED_ARRAY, GET_PATH_FOR_QUERY_PARAM,
//                    GET_SERIALIZED_RECORD_ARRAY
//                                                 ));
//        }
//        if (headersFound) {
//            functionNameList.add(GET_MAP_FOR_HEADERS);
//        }
//        if (pathParametersFound) {
//            functionNameList.add(GET_ENCODED_URI);
//        }
//        if (requestBodyMultipartFormDatafound) {
//            functionNameList.add(CREATE_MULTIPART_BODY_PARTS);
//        }
//
//        List<ModuleMemberDeclarationNode> memberDeclarationNodes = new ArrayList<>();
//        getUtilTypeDeclarationNodes(memberDeclarationNodes);
//
//        Path path = getResourceFilePath();
//
//        Project project = ProjectLoader.loadProject(path);
//        Package currentPackage = project.currentPackage();
//        DocumentId docId = currentPackage.getDefaultModule().documentIds().iterator().next();
//        SyntaxTree syntaxTree = currentPackage.getDefaultModule().document(docId).syntaxTree();
//
//        ModulePartNode modulePartNode = syntaxTree.rootNode();
//        NodeList<ModuleMemberDeclarationNode> members = modulePartNode.members();
//        for (ModuleMemberDeclarationNode node : members) {
//            if (node.kind().equals(SyntaxKind.FUNCTION_DEFINITION)) {
//                for (ChildNodeEntry childNodeEntry : node.childEntries()) {
//                    if (childNodeEntry.name().equals("functionName")) {
//                        if (functionNameList.contains(childNodeEntry.node().get().toString())) {
//                            memberDeclarationNodes.add(node);
//                        }
//                    }
//                }
//            }
//        }
//
//        List<ImportDeclarationNode> imports = new ArrayList<>();
//        if (functionNameList.contains(GET_ENCODED_URI)) {
//            ImportDeclarationNode importForUrl = GeneratorUtils.getImportDeclarationNode(BALLERINA, URL);
//            imports.add(importForUrl);
//        }
//        if (requestBodyMultipartFormDatafound) {
//            ImportDeclarationNode importMime = GeneratorUtils.getImportDeclarationNode(BALLERINA, MIME);
//            imports.add(importMime);
//        }
//
//        NodeList<ImportDeclarationNode> importsList = createNodeList(imports);
//        ModulePartNode utilModulePartNode =
//                createModulePartNode(importsList, createNodeList(memberDeclarationNodes), createToken(EOF_TOKEN));
//        TextDocument textDocument = TextDocuments.from("");
//        SyntaxTree utilSyntaxTree = SyntaxTree.from(textDocument);
//        return utilSyntaxTree.modifyWith(utilModulePartNode);
//    }
//
//    /**
//     * Set the type definition nodes related to the util functions generated.
//     *
//     * @param memberDeclarationNodes {@link ModuleMemberDeclarationNode}
//     */
//    private void getUtilTypeDeclarationNodes(List<ModuleMemberDeclarationNode> memberDeclarationNodes) {
//        if (requestBodyEncodingFound || queryParamsFound || headersFound || requestBodyMultipartFormDatafound) {
//            memberDeclarationNodes.add(getSimpleBasicTypeDefinitionNode());
//        }
//        if (requestBodyEncodingFound || queryParamsFound || requestBodyMultipartFormDatafound) {
//            memberDeclarationNodes.addAll(Arrays.asList(getEncodingRecord(), getStyleEnum()));
//        }
////        if (requestBodyEncodingFound || queryParamsFound) {
////            memberDeclarationNodes.add(getDefaultEncoding());
////        }
//    }
//
//    /**
//     * Generates `Encoding` record.
//     * <pre>
//     *     # Represents encoding mechanism details.
//     *     type Encoding record {
//     *          # Defines how multiple values are delimited
//     *          string style = FORM;
//     *          # Specifies whether arrays and objects should generate separate parameters
//     *          boolean explode = true;
//     *      };
//     * </pre>
//     *
//     * @return {@link TypeDefinitionNode}
//     */
//    private TypeDefinitionNode getEncodingRecord() {
//        // create `style` field
//        List<Node> styleDoc = new ArrayList<>(DocCommentsGenerator.createAPIDescriptionDoc(
//                "Defines how multiple values are delimited", false));
//        MarkdownDocumentationNode styleDocumentationNode = createMarkdownDocumentationNode(createNodeList(styleDoc));
//        MetadataNode styleMetadataNode = createMetadataNode(styleDocumentationNode, createEmptyNodeList());
//        ExpressionNode styleExpressionNode = createRequiredExpressionNode(createIdentifierToken(FORM));
//        RecordFieldWithDefaultValueNode styleFieldNode = createRecordFieldWithDefaultValueNode(styleMetadataNode,
//                null, createToken(STRING_KEYWORD), createIdentifierToken(STYLE),
//                createToken(EQUAL_TOKEN), styleExpressionNode, createToken(SEMICOLON_TOKEN));
//
//        // create `explode` field
//        List<Node> explodeDoc = new ArrayList<>(DocCommentsGenerator.createAPIDescriptionDoc(
//                "Specifies whether arrays and objects should generate as separate fields", false));
//        MarkdownDocumentationNode explodeDocumentationNode = createMarkdownDocumentationNode(
//                createNodeList(explodeDoc));
//        MetadataNode explodeMetadataNode = createMetadataNode(explodeDocumentationNode, createEmptyNodeList());
//        ExpressionNode explodeExpressionNode = createRequiredExpressionNode(createIdentifierToken("true"));
//        RecordFieldWithDefaultValueNode explodeFieldNode = createRecordFieldWithDefaultValueNode(explodeMetadataNode,
//                null, createToken(BOOLEAN_KEYWORD), createIdentifierToken(EXPLODE),
//                createToken(EQUAL_TOKEN), explodeExpressionNode, createToken(SEMICOLON_TOKEN));
//
//        // create `contentType` field
//        List<Node> contentTypeDoc = new ArrayList<>(DocCommentsGenerator.createAPIDescriptionDoc(
//                "Specifies the custom content type", false));
//        MarkdownDocumentationNode contentTypeDocumentationNode = createMarkdownDocumentationNode(
//                createNodeList(contentTypeDoc));
//        MetadataNode contentTypeMetadataNode = createMetadataNode(contentTypeDocumentationNode,
//        createEmptyNodeList());
//        RecordFieldNode contentTypeFieldNode = createRecordFieldNode(contentTypeMetadataNode,
//                null, createToken(STRING_KEYWORD), createIdentifierToken("contentType"),
//                createToken(QUESTION_MARK_TOKEN), createToken(SEMICOLON_TOKEN));
//
//        // create `contentType` field
//        List<Node> headerDoc = new ArrayList<>(DocCommentsGenerator.createAPIDescriptionDoc(
//                "Specifies the custom headers", false));
//        MarkdownDocumentationNode headerDocumentationNode =
//        createMarkdownDocumentationNode(createNodeList(headerDoc));
//        MetadataNode headerMetadataNode = createMetadataNode(headerDocumentationNode, createEmptyNodeList());
//        RecordFieldNode headerFieldNode = createRecordFieldNode(headerMetadataNode, null,
//                createIdentifierToken("map<any>"), createIdentifierToken("headers"),
//                createToken(QUESTION_MARK_TOKEN), createToken(SEMICOLON_TOKEN));
//
//        // Assemble the TypeDefinitionNode
//        List<Node> typeDoc = new ArrayList<>(DocCommentsGenerator.createAPIDescriptionDoc(
//                "Represents encoding mechanism details.", false));
//        MarkdownDocumentationNode typeDocumentationNode = createMarkdownDocumentationNode(createNodeList(typeDoc));
//        MetadataNode typeMetadataNode = createMetadataNode(typeDocumentationNode, createEmptyNodeList());
//        NodeList<Node> fieldNodes = createNodeList(styleFieldNode, explodeFieldNode, contentTypeFieldNode,
//                headerFieldNode);
//        TypeDescriptorNode typeDescriptorNode = createRecordTypeDescriptorNode(createToken(RECORD_KEYWORD),
//                createToken(OPEN_BRACE_TOKEN), fieldNodes, null, createToken(CLOSE_BRACE_TOKEN));
//        return createTypeDefinitionNode(typeMetadataNode, null, createToken(TYPE_KEYWORD),
//                createIdentifierToken(ENCODING), typeDescriptorNode, createToken(SEMICOLON_TOKEN));
//    }
//
//    /**
//     * Generates `EncodingStyles` enum.
//     * <pre>
//     *     enum EncodingStyle {
//     *          DEEPOBJECT,
//     *          FORM,
//     *          SPACEDELIMITED,
//     *          PIPEDELIMITED
//     *     }
//     * </pre>
//     *
//     * @return {@link EnumDeclarationNode}
//     */
//    private EnumDeclarationNode getStyleEnum() {
//        EnumMemberNode deepObject = createEnumMemberNode(null,
//                createIdentifierToken(DEEP_OBJECT), null, null);
//        EnumMemberNode form = createEnumMemberNode(null,
//                createIdentifierToken(FORM), null, null);
//        EnumMemberNode spaceDelimited = createEnumMemberNode(null,
//                createIdentifierToken(SPACE_DELIMITED), null, null);
//        EnumMemberNode pipeDelimited = createEnumMemberNode(null,
//                createIdentifierToken(PIPE_DELIMITED), null, null);
//        SeparatedNodeList<Node> enumMembers = createSeparatedNodeList(deepObject, createToken(COMMA_TOKEN), form,
//                createToken(COMMA_TOKEN), spaceDelimited, createToken(COMMA_TOKEN), pipeDelimited);
//
//        return createEnumDeclarationNode(null, null,
//                createToken(ENUM_KEYWORD), createIdentifierToken(ENCODING_STYLE), createToken(OPEN_BRACE_TOKEN),
//                enumMembers, createToken(CLOSE_BRACE_TOKEN), null);
//    }
//
//    /**
//     * Generates `SimpleBasicType` type.
//     * <pre>
//     *     type SimpleBasicType string|boolean|int|float|decimal;
//     * </pre>
//     *
//     * @return
//     */
//    private TypeDefinitionNode getSimpleBasicTypeDefinitionNode() {
//
//        TypeDescriptorNode typeDescriptorNode = createSingletonTypeDescriptorNode(
//                createSimpleNameReferenceNode(createIdentifierToken("string|boolean|int|float|decimal")));
//        return createTypeDefinitionNode(null, null,
//                createToken(TYPE_KEYWORD), createIdentifierToken("SimpleBasicType"), typeDescriptorNode,
//                createToken(SEMICOLON_TOKEN));
//    }

//    /**
//     * Generates `defaultEncoding` variable declaration node.
//     * <pre>
//     *     final Encoding & readonly defaultEncoding = {};
//     * </pre>
//     *
//     * @return
//     */
//    private ModuleVariableDeclarationNode getDefaultEncoding() {
//        IntersectionTypeDescriptorNode typeName = createIntersectionTypeDescriptorNode(
//                createSimpleNameReferenceNode(createIdentifierToken(ENCODING)), createToken(BITWISE_AND_TOKEN),
//                createSimpleNameReferenceNode(createToken(READONLY_KEYWORD)));
//        CaptureBindingPatternNode bindingPattern = createCaptureBindingPatternNode(
//                createIdentifierToken("defaultEncoding"));
//        TypedBindingPatternNode bindingPatternNode = createTypedBindingPatternNode(typeName, bindingPattern);
//        ExpressionNode expressionNode = createNilLiteralNode(createToken(OPEN_BRACE_TOKEN),
//                createToken(CLOSE_BRACE_TOKEN));
//
//        return createModuleVariableDeclarationNode(null,
//                null, createNodeList(createToken(FINAL_KEYWORD)), bindingPatternNode,
//                createToken(EQUAL_TOKEN), expressionNode, createToken(SEMICOLON_TOKEN));
//    }

    /**
     * Gets the path of the utils_openapi.bal template at the time of execution.
     *
     * @return Path to utils_openapi.bal file in the temporary directory created
     * @throws IOException When failed to get the templates/utils_openapi.bal file from resources
     */
    private Path getResourceFilePath() throws IOException {
        Path path = null;
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("templates/utils_openapi.bal");
        if (inputStream != null) {
            String clientSyntaxTreeString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            Path tmpDir = Files.createTempDirectory(".util-tmp" + System.nanoTime());
            path = tmpDir.resolve("utils.bal");
            try (PrintWriter writer = new PrintWriter(path.toString(), StandardCharsets.UTF_8)) {
                writer.print(clientSyntaxTreeString);
            }
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    FileUtils.deleteDirectory(tmpDir.toFile());
                } catch (IOException ex) {
                    LOGGER.error("Unable to delete the temporary directory : " + tmpDir, ex);
                }
            }));
        }
        return path;
    }
}
