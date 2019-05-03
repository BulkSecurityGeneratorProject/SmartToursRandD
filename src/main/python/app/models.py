import turicreate as tc

# constants
data_folder = 'app/data'
data_file = 'trx_data.csv'

model_folder = 'app/trained_models'
model_name = 'smart-tours-model'


class LogisticClassifier:
    def train(self):
        # Load the data (From an S3 bucket)
        data = tc.SFrame(data_folder + '/' + data_file)

        print('Creating model')
        model = tc.logistic_classifier.create(
            data, target='actionTaken', features=['userDistance'])

        print("Coefficients...")
        print(model.coefficients)

        print("Evaluations...")
        print(model.evaluate(data))

        model.save(model_folder + '/' + model_name)

    def query(self, distances):
        distances = distances.split('|')
        lst = list(map(float, distances))
        test = tc.SFrame({'userDistance': lst, })

        model = tc.load_model(model_folder + '/' + model_name)

        print("Predictions...")
        # get out true or false for each

        pActionTaken = model.predict(test)
        pProbabilities = model.predict(test, output_type='probability')
        pMargins = model.predict(test, output_type='margin')

        data = []
        for index, actionTaken in enumerate(pActionTaken):
            obj = {}
            obj['index'] = index
            obj['distance'] = test['userDistance'][index]
            obj['actionTaken'] = actionTaken
            obj['probability'] = pProbabilities[index]
            obj['margin'] = pMargins[index]
            data.append(obj)

        return data
