/*
 * Copyright 2013 Esri.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.esri.gpt.framework.ckan;

import com.esri.gpt.framework.util.Val;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * package list adaptor.
 */
/*package*/class CkanPackageListAdaptor implements CkanPackageList {
  private static final Logger LOG = Logger.getLogger(CkanPackageListAdaptor.class.getName());
  private final JSONObject packageListJson;

  public CkanPackageListAdaptor(JSONObject packageListJson) {
    this.packageListJson = packageListJson;
  }

  @Override
  public List<String> getPackagesIds() {
    ArrayList<String> packagesIds = new ArrayList<String>();
    if (packageListJson.has("result")) {
      try {
        JSONArray result = packageListJson.getJSONArray("result");
        for (int i=0; i<result.length(); i++) {
          if (result.isNull(i)) continue;
          String id = Val.chkStr(result.getString(i));
          if (id.isEmpty()) continue;
          packagesIds.add(id);
        }
      } catch (JSONException ex) {
        LOG.log(Level.WARNING,"Invalid package list format", ex);
      }
    }
    return packagesIds;
  }
  
}
