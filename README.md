# nuxeo-soffice-thumbnail-converters

## About / Synopsis

This plugin addresses the issue of generating the **thumbnail** for large **Office** documents. Currently the **Office** document is converted to a **PDF** then an image is generated from its first page. This can be an issue with large **Office** documents, converting the whole document to **PDF** is over-the-top.

This plugins provides **converters** that can be used to bypass the **PDF** conversion, and directly generate the **Thumbnail** image using the `soffice` command.

This bypass can be enabled based on the document's **MIME type** by adding it to configuration variable `nuxeo.thumbnail.soffice.converter.mimetypes`, see exmaple below.

## Requirements

Building requires the following software:

* git
* maven

## Build

```
git clone ...
cd nuxeo-soffice-thumbnail-converters

mvn clean install
```

## Installation

```
nuxeoctl mp-install nuxeo-soffice-thumbnail-converters/nuxeo-soffice-thumbnail-converters-package/target/nuxeo-soffice-thumbnail-converters-package-*.zip
```

## Configuration

Contribute a **XML extension** in your bundle or define a **Studio XML extension** with the following **XML**:

### `nuxeo.conf` or configuration template

Here is how to enable the bypass for `.ppt` and `.pptx` in `nuxeo.conf`:
```
nuxeo.thumbnail.soffice.converter.mimetypes=application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation
```

### **XML contribution**

See `nuxeo-soffice-thumbnail-converters/nuxeo-soffice-thumbnail-converters-core/src/samples/soffice-thumbnail-converters-config.xml`.

## Support

**These features are not part of the Nuxeo Production platform, they are not supported**

These solutions are provided for inspiration and we encourage customers to use them as code samples and learning resources.

This is a moving project (no API maintenance, no deprecation process, etc.) If any of these solutions are found to be useful for the Nuxeo Platform in general, they will be integrated directly into platform, not maintained here.


## License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

## About Nuxeo

Nuxeo Platform is an open source Content Services platform, written in Java. Data can be stored in both SQL & NoSQL databases.

The development of the Nuxeo Platform is mostly done by Nuxeo employees with an open development model.

The source code, documentation, roadmap, issue tracker, testing, benchmarks are all public.

Typically, Nuxeo users build different types of information management solutions for [document management](https://www.nuxeo.com/solutions/document-management/), [case management](https://www.nuxeo.com/solutions/case-management/), and [digital asset management](https://www.nuxeo.com/solutions/dam-digital-asset-management/), use cases. It uses schema-flexible metadata & content models that allows content to be repurposed to fulfill future use cases.

More information is available at [www.nuxeo.com](https://www.nuxeo.com).

