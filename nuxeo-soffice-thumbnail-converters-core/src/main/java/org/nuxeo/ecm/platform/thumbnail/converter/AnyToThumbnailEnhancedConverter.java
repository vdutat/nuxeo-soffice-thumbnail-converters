package org.nuxeo.ecm.platform.thumbnail.converter;

import static org.nuxeo.ecm.platform.thumbnail.ThumbnailConstants.ANY_TO_PDF_TO_THUMBNAIL_CONVERTER_NAME;
import static org.nuxeo.ecm.platform.thumbnail.ThumbnailConstants.PDF_AND_IMAGE_TO_THUMBNAIL_CONVERTER_NAME;

import java.io.Serializable;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.blobholder.BlobHolder;
import org.nuxeo.ecm.core.convert.api.ConversionService;
import org.nuxeo.ecm.core.convert.extension.ConverterDescriptor;
import org.nuxeo.runtime.api.Framework;

/**
 * Enhanced converter choosing the correct convert to generate a thumbnail according to the Blob's mime type.
 *
 * @since 10.10
 */
public class AnyToThumbnailEnhancedConverter extends AnyToThumbnailConverter {

    public static final String OFFICE_TO_THUMBNAIL_CONVERTER_NAME = "OfficeToThumbnail";

    public static final String OFFICE_TO_ORIGINAL_THUMBNAIL_CONVERTER_NAME = "OfficeToOriginalThumbnail";

    private static final Logger log = LogManager.getLogger(AnyToThumbnailConverter.class);

    @Override
    public void init(ConverterDescriptor descriptor) {
        super.init(descriptor);
    }

    @Override
    public BlobHolder convert(BlobHolder blobHolder, Map<String, Serializable> parameters) {
        if (log.isDebugEnabled()) {
            parameters.entrySet().stream().forEach(e-> log.debug("<convert> " + e));
        }
        Blob sourceBlob = blobHolder.getBlob();
        if (sourceBlob == null) {
            return null;
        }
        String mimeType = sourceBlob.getMimeType();
        if (mimeType == null) {
            return null;
        }
        ConversionService conversionService = Framework.getService(ConversionService.class);
        String converterName = null;
        if ((mimeType.startsWith("image/") || PDF_MIME_TYPE_PATTERN.matcher(mimeType).matches())
                && conversionService.isConverterAvailable(PDF_AND_IMAGE_TO_THUMBNAIL_CONVERTER_NAME, true)
                                    .isAvailable()) {
            converterName = PDF_AND_IMAGE_TO_THUMBNAIL_CONVERTER_NAME;
        } else if (conversionService.isSourceMimeTypeSupported(OFFICE_TO_ORIGINAL_THUMBNAIL_CONVERTER_NAME, mimeType)
                && conversionService.isConverterAvailable(OFFICE_TO_ORIGINAL_THUMBNAIL_CONVERTER_NAME, true).isAvailable()) {
            converterName = OFFICE_TO_THUMBNAIL_CONVERTER_NAME;
        } else if (conversionService.isSourceMimeTypeSupported(ANY_TO_PDF_CONVERTER_NAME, mimeType)
                && conversionService.isConverterAvailable(ANY_TO_PDF_CONVERTER_NAME, true).isAvailable()) {
            converterName = ANY_TO_PDF_TO_THUMBNAIL_CONVERTER_NAME;
        }
        return converterName == null ? null : conversionService.convert(converterName, blobHolder, parameters);
    }
}
