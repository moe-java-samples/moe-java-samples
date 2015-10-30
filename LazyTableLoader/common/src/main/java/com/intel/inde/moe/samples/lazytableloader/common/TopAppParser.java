// Copyright (c) 2015, Intel Corporation
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
// 1. Redistributions of source code must retain the above copyright
// notice, this list of conditions and the following disclaimer.
// 2. Redistributions in binary form must reproduce the above
// copyright notice, this list of conditions and the following disclaimer
// in the documentation and/or other materials provided with the
// distribution.
// 3. Neither the name of the copyright holder nor the names of its
// contributors may be used to endorse or promote products derived from
// this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package com.intel.inde.moe.samples.lazytableloader.common;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class TopAppParser {

    private ArrayList<AppRecord> items = new ArrayList<>();

    public TopAppParser(String string) throws Exception {
        URL url = new URL(string);
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        DocumentBuilderFactory builderf = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder builder = builderf.newDocumentBuilder();
        Document doc = builder.parse(in);

        NodeList list = doc.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            if (list.item(i).getNodeName().equals("feed")) {
                handleFeedNode(list.item(i));
            }
        }
    }

    private void handleFeedNode(Node feed) {
        NodeList list = feed.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            if (list.item(i).getNodeName().equals("entry")) {
                handleAppNode(list.item(i));
            }
        }
    }

    private void handleAppNode(Node item) {
        AppRecord fi = new AppRecord();
        NodeList list = item.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            Node node = list.item(i);
            if (node.getNodeName().equals("im:name")) {
                fi.setAppName(node.getTextContent());
            } else if (node.getNodeName().equals("im:artist")) {
                fi.setArtist(node.getTextContent());
            } else if (node.getNodeName().equals("im:image")) {
                fi.setImageURLString(node.getTextContent());
            } else if (node.getNodeName().equals("id")) {
                fi.setAppURLString(node.getTextContent());
            }
        }
        items.add(fi);
    }

    public ArrayList<AppRecord> getItems() {
        return items;
    }
}
