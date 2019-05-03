import turicreate as tc

data_folder = 'app/data'
data_file = 'trx_data.csv'

model_folder = 'app/trained_models'
model_name = 'smart-tours-model'

class LogisticClassifier:
    def train(self):
        data = tc.SFrame(data_folder + '/' + data_file)
        print('Creating model')
        model = tc.logistic_classifier.create(
            data, target='actionTaken', features=['userDistance'])
        print("Coefficients...")
        print(model.coefficients)
        print("Evaluations...")
        print(model.evaluate(data))
        model.save(model_folder + '/' + model_name)

    def query(self, inputData):
        ids = list()
        distances = list()
        for index, thisItem in enumerate(inputData):
            ids.append(thisItem['itemId'])
            distances.append(thisItem['distance'])

        test = tc.SFrame({'userDistance': distances, })

        model = tc.load_model(model_folder + '/' + model_name)

        pActionTaken = model.predict(test)
        pProbabilities = model.predict(test, output_type='probability')
        pMargins = model.predict(test, output_type='margin')

        data = []
        for index, actionTaken in enumerate(pActionTaken):
            obj = {}
            obj['index'] = index
            obj['itemId'] = ids[index]
            obj['distance'] = distances[index]
            obj['actionTaken'] = actionTaken.lower() == 'true'
            obj['probability'] = pProbabilities[index]
            obj['margin'] = pMargins[index]
            data.append(obj)

        return data
