package com.deepanshu.model;

import java.math.BigDecimal;

/*
 * @author Deepanshu Bhatti
 */
public class SourceDestinationDistance {

    private ReferenceData source;
    private ReferenceData destination;
    private BigDecimal distance;

    public SourceDestinationDistance(ReferenceData source, ReferenceData destination, BigDecimal distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public ReferenceData getSource() {
        return source;
    }

    public void setSource(ReferenceData source) {
        this.source = source;
    }

    public ReferenceData getDestination() {
        return destination;
    }

    public void setDestination(ReferenceData destination) {
        this.destination = destination;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }
}
