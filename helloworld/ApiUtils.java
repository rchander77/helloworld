package com.example.helloworld;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.io.IOException;

public class ApiUtils {
    public static <T> T patch(Object entity, String patchJson, Class<T> valueType) throws ApplicationException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode patchAsNode = mapper.readTree(patchJson);
            JsonPatch patchTool = JsonPatch.fromJson(patchAsNode);
            JsonNode entityAsNode = mapper.valueToTree(entity);
            JsonNode modifiedAsNode = patchTool.apply(entityAsNode);
            return mapper.treeToValue(modifiedAsNode, valueType);
        } catch (IOException | JsonPatchException eX) {
            throw new ApplicationException("An error occurred while applying patch on the target json", eX);
        }
    }
}