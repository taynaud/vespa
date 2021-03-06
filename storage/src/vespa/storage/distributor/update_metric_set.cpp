// Copyright 2018 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root.

#include "update_metric_set.h"
#include <vespa/metrics/loadmetric.hpp>
#include <vespa/metrics/summetric.hpp>

namespace storage {

using metrics::MetricSet;

UpdateMetricSet::UpdateMetricSet(MetricSet* owner)
    : PersistenceOperationMetricSet("updates", owner),
      diverging_timestamp_updates("diverging_timestamp_updates", {},
                                  "Number of updates that report they were performed against "
                                  "divergent version timestamps on different replicas", this)
{
}

UpdateMetricSet::~UpdateMetricSet() = default;

MetricSet *
UpdateMetricSet::clone(std::vector<Metric::UP>& ownerList, CopyType copyType,
                        MetricSet* owner, bool includeUnused) const
{
    if (copyType == INACTIVE) {
        return MetricSet::clone(ownerList, INACTIVE, owner, includeUnused);
    }
    return static_cast<MetricSet*>((new UpdateMetricSet(owner))->assignValues(*this));
}

}

template class metrics::LoadMetric<storage::UpdateMetricSet>;
template class metrics::SumMetric<storage::UpdateMetricSet>;
